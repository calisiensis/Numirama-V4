package pl.org.schroeder.numirama.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.org.schroeder.numirama.dao.CoinDAO;
import pl.org.schroeder.numirama.entity.Coin;

@Service
public class CoinServiceImpl implements CoinService {

	// need to inject customer dao
	@Autowired
	private CoinDAO coinDAO;

	@Override
	@Transactional
	public List<Coin> getCoins() {
		return coinDAO.getCoins();
	}

	@Override
	@Transactional
	public void saveCoin(Coin theCoin) {

		coinDAO.saveCoin(theCoin);
	}

	@Override
	@Transactional
	public Coin getCoin(int theId) {

		return coinDAO.getCoin(theId);
	}

	@Override
	@Transactional
	public void deleteCoin(int theId) {

		coinDAO.deleteCoin(theId);
	}

	@Override
	@Transactional
	public List<Coin> searchForCoin(String searchedText) {

		return coinDAO.searchForCoin(searchedText);
	}

	@Override
	@Transactional
	public List<Coin> getPaginatedCoins(int pageNumber) {
		List<Coin> coins = coinDAO.getPaginatedCoins(pageNumber);
		return coins;
	}

	@Override
	@Transactional
	public long getCoinsQuantity() {
		long count = coinDAO.getCoinsQuantity();
		return count;
	}

}
