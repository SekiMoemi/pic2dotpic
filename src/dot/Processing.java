package dot;

public abstract class Processing extends Image {
	protected Image image;

	protected Processing(Image image) {
		this.image = image;
	}
}