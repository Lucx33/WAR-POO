package view;

public class IniciaInterface {
	Menu f;
	public IniciaInterface(PlayersInfo playersInfo) {
		this.f = new Menu(playersInfo);
		f.setTitle("War");
		f.setVisible(true);

	}
}
