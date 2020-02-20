package pl.org.schroeder.numirama.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.org.schroeder.numirama.entity.Coin;

@Repository
public class CoinDAOImpl implements CoinDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	private int pageSize = 25;

	@Override
	public List<Coin> getCoins() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Coin> theQuery = currentSession.createQuery(
				"from Coin order by krajEmitent asc, nazwaWaluty asc, nominalWaluty desc, rokWybicia asc, cena desc",
				Coin.class);

		// execute query and get result list
		List<Coin> coins = theQuery.getResultList();

		// return the results
		return coins;
	}

	@Override
	public void saveCoin(Coin theCoin) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theCoin);

	}

	@Override
	public Coin getCoin(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Coin theCoin = currentSession.get(Coin.class, theId);

		return theCoin;
	}

	@Override
	public void deleteCoin(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Coin where id=:coinId");
		theQuery.setParameter("coinId", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Coin> searchForCoin(String searchedText) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		if (searchedText != null && searchedText.trim().length() > 0) {
			theQuery = currentSession.createQuery(
					"from Coin where lower(krajEmitent) like :Tekst or lower(nazwaWaluty) like :Tekst or lower(material) like :Tekst or lower(stan) like :Tekst or lower(uwagi) like :Tekst or lower(status) like :Tekst or nominalWaluty like :Tekst or rokWybicia like :Tekst or srednica like :Tekst or waga like :Tekst or cena like:Tekst",
					Coin.class);
			theQuery.setParameter("Tekst", "%" + searchedText.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from Coin", Coin.class);
		}

		List<Coin> coins = theQuery.getResultList();
		return coins;
	}

	@Override
	public List<Coin> getPaginatedCoins(int pageNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Coin> query = currentSession.createQuery(
				"from Coin order by krajEmitent asc, nazwaWaluty asc, nominalWaluty desc, rokWybicia asc, cena desc",
				Coin.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Coin> coins = query.getResultList();
		return coins;
	}

	@Override
	public long getCoinsQuantity() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Long> countQuery = currentSession.createQuery("select count(1) from Coin", Long.class);
		long count = countQuery.getSingleResult().longValue();
		return count;
	}

}
