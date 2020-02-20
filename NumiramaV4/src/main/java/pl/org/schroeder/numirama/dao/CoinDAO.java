package pl.org.schroeder.numirama.dao;

import java.util.List;

import pl.org.schroeder.numirama.entity.Coin;

public interface CoinDAO {

	public List<Coin> getCoins();

	public void saveCoin(Coin theCoin);

	public Coin getCoin(int theId);

	public void deleteCoin(int theId);

	public List<Coin> searchForCoin(String searchedText);

	public List<Coin> getPaginatedCoins(int pageNumber);

	public long getCoinsQuantity();

}
