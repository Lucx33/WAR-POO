package view;

public class IniciaInterface {
	public Menu f;
	public IniciaInterface(PlayersInfo playersInfo) {
		this.f = new Menu(playersInfo);
		f.setTitle("War");
		f.setVisible(true);

	}
}
