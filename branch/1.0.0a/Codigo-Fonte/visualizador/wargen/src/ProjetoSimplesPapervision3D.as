package

{
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.PixelSnapping;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.media.Camera;
	import flash.media.Video;
	import flash.net.URLLoader;
	import flash.net.URLLoaderDataFormat;
	import flash.net.URLRequest;
	import org.libspark.flartoolkit.core.analyzer.raster.threshold.FLARRasterThresholdAnalyzer_SlidePTile;
	import org.libspark.flartoolkit.core.FLARCode;
	import org.libspark.flartoolkit.core.param.FLARParam;
	import org.libspark.flartoolkit.core.raster.rgb.FLARRgbRaster_BitmapData;
	import org.libspark.flartoolkit.core.transmat.FLARTransMatResult;
	import org.libspark.flartoolkit.detector.FLARSingleMarkerDetector;
	import org.libspark.flartoolkit.support.pv3d.FLARBaseNode;
	import org.libspark.flartoolkit.support.pv3d.FLARCamera3D;
	import org.papervision3d.objects.parsers.DAE;
	import org.papervision3d.objects.parsers.KMZ;
	import org.papervision3d.objects.parsers.Max3DS;
	import org.papervision3d.render.LazyRenderEngine;
	import org.papervision3d.scenes.Scene3D;
	import org.papervision3d.view.Viewport3D;

	public class ProjetoSimplesPapervision3D extends Sprite
	{
	
		private var arquivoParametrosCamera:String = "";
		private var modeloCarregar:Modelo = null;
		private var marcadorCarregar:Marcador = null;
		private var interacao:Interacao = null;
		private var interacaoSimples:InteracaoSimplesPapervision3D = null;
		private var alturaTela:int = 500;
		private var larguraTela:int = 500;
		private var larguraMarcador:int = 80;
		private var isMarcadorDetectado:Boolean = false;
		private var limiarizacao:int = 80;
		
		//flash
		private var carregadorURL:URLLoader = null;
		private var camera:Camera;
		private var video:Video;
		private var capturaBitmap:Bitmap;
		
		//flar
		private var parametrosCamera:FLARParam = null;
		private var marcador:FLARCode = null;
		private var baseModelo:FLARBaseNode = null;
		private var camera3D:FLARCamera3D = null;
		private var analisadorLimiarizacao:FLARRasterThresholdAnalyzer_SlidePTile = null;
		private var detectorUnicoMarcador:FLARSingleMarkerDetector = null;
		private var rasterizadorRGB:FLARRgbRaster_BitmapData = null;
		private var matrizTransformacao:FLARTransMatResult = null;
		
		
		//papervision3D
		private var cena3D:Scene3D = null;
		private var viewport3D:Viewport3D = null;
		private var renderizador:LazyRenderEngine = null;
		private var modeloDAE:DAE = null;
		private var modeloKMZ:KMZ = null;
		private var modelo3DS:Max3DS = null;
		
		public function ProjetoSimplesPapervision3D() {
			
		}
		
		public function carregar(arquivoParametrosCamera:String, marcador:Marcador, modelo:Modelo, interacao:Interacao):void {
			
			this.arquivoParametrosCamera = arquivoParametrosCamera;
			this.marcadorCarregar = marcador;
			this.modeloCarregar = modelo;
			this.interacao = interacao;
			
			carregadorURL = new URLLoader();
			
			carregarParametrosCamera();
			
		}
		
		public function carregarParametrosCamera():void {
			
			carregadorURL.dataFormat = URLLoaderDataFormat.BINARY;
			carregadorURL.addEventListener(Event.COMPLETE, onParametrosCameraCarregados);
			carregadorURL.load(new URLRequest(arquivoParametrosCamera));
			
		}
		
		public function onParametrosCameraCarregados(e:Event):void {
			
			carregadorURL.removeEventListener(Event.COMPLETE, onParametrosCameraCarregados);
			
			parametrosCamera = new FLARParam();
			parametrosCamera.loadARParam(carregadorURL.data);
			parametrosCamera.changeScreenSize(larguraTela, alturaTela);
			
			carregarMarcador();
			
		}
		
		public function carregarMarcador():void {
			
			carregadorURL.dataFormat = URLLoaderDataFormat.TEXT;
			carregadorURL.addEventListener(Event.COMPLETE, onMarcadorCarregado);
			carregadorURL.load(new URLRequest(marcadorCarregar.arquivo.caminho));
			
		}
		
		public function onMarcadorCarregado(e:Event):void {
			
			carregadorURL.removeEventListener(Event.COMPLETE, onMarcadorCarregado);
			
			marcador = new FLARCode(16, 16);
			marcador.loadARPatt(carregadorURL.data);
			
			carregadorURL = null;
			
			iniciar();
		}
		
		protected function iniciar():void {
			configurarWebcam();
			configurarVideo();
			configurarPapervision3D();
			configurarModeloVirtual();
			configurarFLARToolKit();
			configurarInteracoes();
			
			addChild(capturaBitmap);
			addChild(viewport3D);
			
			addEventListener(Event.ENTER_FRAME, onFrameCapturado);
		}
		
		private function configurarWebcam():void {
			
			camera = Camera.getCamera();
			
			if (!camera) {
				throw new Error('Webcam não encontrada!');
			}
			
			camera.setMode(larguraTela, alturaTela, 60, true);
			
		}
		
		private function configurarVideo():void {
			
			video = new Video(larguraTela, alturaTela);
			video.attachCamera(camera);
			
		}
		
		private function configurarPapervision3D():void {
			
			cena3D = new Scene3D();
			camera3D = new FLARCamera3D(parametrosCamera);
			viewport3D = new Viewport3D(larguraTela, alturaTela);
			renderizador = new LazyRenderEngine(cena3D, camera3D, viewport3D);
		}
		
		private function configurarModeloVirtual():void {
			
			baseModelo = new FLARBaseNode();
			
			switch (modeloCarregar.tipo) {
				case "dae":
					modeloDAE = new DAE();
					modeloDAE.load(modeloCarregar.arquivo.caminho)
					modeloDAE.rotationX = 90;
					modeloDAE.scale = (modeloDAE.scale > 5 ) ? 5 : ((modeloDAE.scale < 5) ? 5 : modeloDAE.scale);			
					baseModelo.addChild(modeloDAE, "modelo");					
					break;
				case "kmz":
					modeloKMZ = new KMZ();
					modeloKMZ.load(modeloCarregar.arquivo.caminho)
					modeloKMZ.rotationX = 90;
					modeloKMZ.scale = (modeloKMZ.scale > 5) ? 5 : ((modeloKMZ.scale < 5) ? 5 : modeloKMZ.scale);
					baseModelo.addChild(modeloKMZ, "modelo");					
					break;
				case "3ds":
					modelo3DS = new Max3DS();
					modelo3DS.load(modeloCarregar.arquivo.caminho);
					modelo3DS.rotationX = 90;
					modelo3DS.scale = (modelo3DS.scale > 5 ) ? 5 : ((modelo3DS.scale < 5) ? 5 : modelo3DS.scale);
					baseModelo.addChild(modelo3DS, "modelo");		
					break;
			}
			
			cena3D.addChild(baseModelo);
		}
		
		private function configurarFLARToolKit():void {
			
			analisadorLimiarizacao = new FLARRasterThresholdAnalyzer_SlidePTile(15, 4);
			
			capturaBitmap = new Bitmap(
								new BitmapData(larguraTela, alturaTela, false, 0),
								PixelSnapping.AUTO,
								true);
								
			capturaBitmap.width = larguraTela;
			capturaBitmap.height = alturaTela;
			
			detectorUnicoMarcador = new FLARSingleMarkerDetector(
											parametrosCamera, marcador, larguraMarcador);
			
			rasterizadorRGB = new FLARRgbRaster_BitmapData(capturaBitmap.bitmapData);
			matrizTransformacao = new FLARTransMatResult();
			
		}
		
		private function configurarInteracoes():void {
			
			interacaoSimples = new InteracaoSimplesPapervision3D();
			interacaoSimples.iniciar(baseModelo, stage, interacao);
			
		}
		
		private function onFrameCapturado(e:Event):void {
			
			capturaBitmap.bitmapData.draw(video);
			
			isMarcadorDetectado = (detectorUnicoMarcador.detectMarkerLite(rasterizadorRGB, limiarizacao)) &&
								  (detectorUnicoMarcador.getConfidence() >= 0.3);
			
			if (isMarcadorDetectado) {
				detectorUnicoMarcador.getTransformMatrix(matrizTransformacao);
				baseModelo.setTransformMatrix(matrizTransformacao);
			}
			else {
				limiarizacao = analisadorLimiarizacao.analyzeRaster(rasterizadorRGB);
			}
			
			renderizador.render();
		}
	}

}