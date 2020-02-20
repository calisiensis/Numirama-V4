package pl.org.schroeder.numirama.service;

import java.util.List;

import pl.org.schroeder.numirama.entity.Coin;

public interface CoinService {

	public List<Coin> getCoins();

	public void saveCoin(Coin theCoin);

	public Coin getCoin(int theId);

	public void deleteCoin(int theId);

	public List<Coin> searchForCoin(String searchedText);

	public List<Coin> getPaginatedCoins(int pageNumber);

	public long getCoinsQuantity();

}
