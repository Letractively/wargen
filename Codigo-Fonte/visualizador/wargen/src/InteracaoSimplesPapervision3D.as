package  
{
	import flash.display.Sprite;
	import flash.display.Stage;
	import flash.ui.Keyboard;
	import flash.events.KeyboardEvent;
	import org.libspark.flartoolkit.support.pv3d.FLARBaseNode;
	
	public class InteracaoSimplesPapervision3D extends Sprite 
	{
		
		private var baseModelo:FLARBaseNode = null;
		private var interacao:Interacao = null;
		
		public function InteracaoSimplesPapervision3D() {
			
		}
		
		public function iniciar(baseModelo:FLARBaseNode, stage:Stage, interacao:Interacao):void {
			this.baseModelo = baseModelo;
			this.interacao = interacao;
			
			stage.addEventListener(KeyboardEvent.KEY_DOWN, verificarAcoes);
			
			if (interacao.isMovimento) {			
				stage.addEventListener(KeyboardEvent.KEY_DOWN, verificarAcoesMovimento);
			}
			
			if (interacao.isRotacao) {
				stage.addEventListener(KeyboardEvent.KEY_DOWN, verificarAcoesRotacao);
			}
			
			if (interacao.isEscala) {
				stage.addEventListener(KeyboardEvent.KEY_DOWN, verificarAcoesEscala);
			}
		}
		
		
		private function verificarAcoes(event:KeyboardEvent):void {
			if (event.type == KeyboardEvent.KEY_DOWN) {
				switch (event.keyCode) {
					case Keyboard.ESCAPE:
						baseModelo.getChildByName("modelo").x = 0;
						baseModelo.getChildByName("modelo").y = 0;
						baseModelo.getChildByName("modelo").z = 0;
						baseModelo.getChildByName("modelo").scale = 5;
						baseModelo.getChildByName("modelo").rotationX = 90;
						baseModelo.getChildByName("modelo").rotationY = 0;
						baseModelo.getChildByName("modelo").rotationZ = 0;
						break;
				}
			}
		}
		
		private function verificarAcoesMovimento(event:KeyboardEvent):void {
			if (event.type == KeyboardEvent.KEY_DOWN) {
				switch (event.keyCode) {
					case Keyboard.UP:							
						if (event.ctrlKey) {								
							baseModelo.getChildByName("modelo").z += 5;								
						}
						else {	
							baseModelo.getChildByName("modelo").y += 5;	
						}
						break;
					case Keyboard.LEFT:
							baseModelo.getChildByName("modelo").x += 5;
						break;
					case Keyboard.RIGHT:
							baseModelo.getChildByName("modelo").x -= 5;
						break;
					case Keyboard.DOWN:							
						if (event.ctrlKey) {
							if (baseModelo.getChildByName("modelo").z - 5 >= 0) {
								baseModelo.getChildByName("modelo").z -= 5;
							}
						}
						else {	
							baseModelo.getChildByName("modelo").y -= 5;	
						}
						break;
				}
			}
		}
		
		private function verificarAcoesRotacao(event:KeyboardEvent):void {
			if (event.type == KeyboardEvent.KEY_DOWN) {
				switch (event.keyCode) {
					case Keyboard.HOME:
							baseModelo.getChildByName("modelo").rotationX -= 5;
						break;
					case Keyboard.PAGE_DOWN:
							baseModelo.getChildByName("modelo").rotationZ += 5;
						break;
					case Keyboard.DELETE:
							baseModelo.getChildByName("modelo").rotationX -= 5;
						break;
					case Keyboard.END:
							baseModelo.getChildByName("modelo").rotationX += 5;
						break;
					case Keyboard.PAGE_UP:
							baseModelo.getChildByName("modelo").rotationY -= 5;
						break;
					case Keyboard.INSERT:
							baseModelo.getChildByName("modelo").rotationX += 5;
						break;
				}
			}
		}
		
		private function verificarAcoesEscala(event:KeyboardEvent):void {
			if (event.type == KeyboardEvent.KEY_DOWN) {
				switch (event.keyCode) {
					case Keyboard.NUMPAD_ADD: //TECLA +
						baseModelo.getChildByName("modelo").scale += 1;
						break;
					case Keyboard.NUMPAD_SUBTRACT: //TECLA -
						if (baseModelo.getChildByName("modelo").scale > 1) {
							baseModelo.getChildByName("modelo").scale -= 1;
						}
						break;
				}
			}
		}
	}

}