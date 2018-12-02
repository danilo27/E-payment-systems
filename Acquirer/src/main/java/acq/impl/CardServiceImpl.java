package acq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acq.model.Card;
import acq.repositories.CardRepository;
import acq.services.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repo;

	@Override
	public Card findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Card> findAll() {
		return repo.findAll();
	}

	@Override
	public Card save(Card arg) {
		return repo.save(arg);
	}
}
