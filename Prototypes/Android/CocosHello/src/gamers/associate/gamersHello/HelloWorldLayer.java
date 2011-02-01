package gamers.associate.gamersHello;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGPoint;

public class HelloWorldLayer extends CCLayer {
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new HelloWorldLayer();
		scene.addChild(layer);
		
		return scene;
	}
	
	protected HelloWorldLayer() {
		this.setIsTouchEnabled(true);		
		CCLabel label = CCLabel.makeLabel("Hello Word !", "DroidSans", 24);
		addChild(label, 0);
		label.setPosition(CGPoint.ccp(160, 240));
	}
}
