package dot;

import java.awt.image.BufferedImage;

public class DotProcessing extends Processing{
	private int dotSize;
	public DotProcessing(Image image, int dotSize) {
		super(image);
		this.dotSize = dotSize;
	}

	@Override
	public BufferedImage getImage() {
		BufferedImage bi = image.getImage();
		for(int i = 0; i < bi.getWidth(); i++) {
			for(int j = 0; j < bi.getHeight(); j++) {
//				dotの大きさで同じ色にする
				if(i % dotSize != 0 || j % dotSize != 0) {
					bi.setRGB(i, j, bi.getRGB(i - i % dotSize,j - j % dotSize));
				}
			}
		}
		return bi;
	}

}