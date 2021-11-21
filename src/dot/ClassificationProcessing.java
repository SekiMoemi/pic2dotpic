package dot;

import java.awt.image.BufferedImage;

public class ClassificationProcessing extends Processing {
	protected ClassificationProcessing(Image image) {
		super(image);
	}

	@Override
	public BufferedImage getImage() {
		BufferedImage bi = image.getImage();
		for(int i = 0; i < bi.getWidth(); i++) {
			for(int j = 0; j < bi.getHeight(); j ++) {
				int color = bi.getRGB(i, j);
//				rgbそれぞれを8段階にする
				int r = color>>16&0xff;
				int g = color>>8&0xff;
				int b = color & 0xff;
				r = classification(r);
				g = classification(g);
				b = classification(b);
				color = 0x000000| r << 16 | g << 8| b;
//				結果の色を設定
				bi.setRGB(i, j, color);
			}
		}
		return bi;
	}
	public static int classification(int a) {
		if(a < 32) {
			a = 31;
		} else if(a < 64){
			a = 63;
		} else if(a < 96) {
			a = 95;
		} else if(a < 128) {
			a = 127;
		} else if(a < 160) {
			a = 159;
		} else if(a < 192) {
			a = 191;
		} else if(a < 224) {
			a = 223;
		} else {
			a = 255;
		}
		return a;
	}
}