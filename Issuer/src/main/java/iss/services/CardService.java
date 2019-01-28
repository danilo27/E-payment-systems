package iss.services;

import java.util.List;

import iss.model.Card;

public interface CardService {
	Card findOne(Long id);
	List<Card> findAll();
	Card save(Card arg);
 
	//Card findByMerchantId(String merchantId);
}
