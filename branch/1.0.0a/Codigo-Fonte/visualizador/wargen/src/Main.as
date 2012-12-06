package  
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	public class Main extends Sprite
	{
		private var modelo:Modelo = null;
		private var marcador:Marcador = null;
		private var interacao:Interacao = null;
		
		private var urlLoader:URLLoader;
		private var parametros:XML;		
		private var projetoSimples:ProjetoSimplesPapervision3D = null;
		
		public function Main() 
		{			
			urlLoader = new URLLoader();
			urlLoader.load(new URLRequest(root.loaderInfo.parameters.arquivoParametros));
			urlLoader.addEventListener(Event.COMPLETE, onXmlParametrosCarregado);
		}
			
		private function onXmlParametrosCarregado(e:Event):void 
		{						
			parametros = new XML(urlLoader.data);
			
			modelo = new Modelo();
			modelo.arquivo = new Arquivo();
			modelo.arquivo.nome = parametros.modelo[0].@nome;
			modelo.arquivo.extensao = parametros.modelo[0].@extensao;
			modelo.arquivo.caminho = parametros.modelo[0].@caminho;
			modelo.tipo = parametros.modelo[0].@tipo;
			
			marcador = new Marcador();
			marcador.arquivo = new Arquivo();
			marcador.arquivo.nome = parametros.marcador[0].@nome;
			marcador.arquivo.extensao = parametros.marcador[0].@extensao;
			marcador.arquivo.caminho = parametros.marcador[0].@caminho;
			
			interacao = new Interacao();
			interacao.isMovimento = parametros.interacao[0].@isMovimento;
			interacao.isRotacao = parametros.interacao[0].@isRotacao;
			interacao.isEscala = parametros.interacao[0].@isEscala;
			
			projetoSimples = new ProjetoSimplesPapervision3D();
			projetoSimples.carregar("camera_para.dat", marcador, modelo, interacao);
			
			this.addChild(projetoSimples);
		}
	}

}