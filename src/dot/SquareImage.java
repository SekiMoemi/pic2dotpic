package dot;

import java.awt.image.BufferedImage;

public class SquareImage extends Image {
	BufferedImage bi;

	public SquareImage(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		int dif = Math.abs(width - height);
		if(width > height) {
			this.bi = new BufferedImage(height, height, BufferedImage.TYPE_INT_RGB);
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					if(i >= dif/2 && i < width - dif/2) {
						this.bi.setRGB(i - dif/2, j, bi.getRGB(i, j));
					}
				}
			}
		}	else if(width < height) {
			this.bi = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					if(j >= dif/2 && j < height - dif/2) {
						this.bi.setRGB(i, j- dif/2, bi.getRGB(i, j));
					}
				}
			}
		}
	}

	@Override
	public BufferedImage getImage() {
		return bi;
	}
}