package dot;

import java.awt.image.BufferedImage;

public class RectImage extends Image{
	BufferedImage bi;

	public RectImage(BufferedImage bi) {
		this.bi =  new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < bi.getWidth(); i++) {
			for(int j = 0; j < bi.getHeight(); j++) {
				this.bi.setRGB(i, j, bi.getRGB(i,j));
			}
		}
	}

	@Override
	public BufferedImage getImage() {
		return this.bi;
	}
}
