package tn.esprit.rh.achat.services;


import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
    ProduitRepository produitRepository;
	@Autowired
    StockRepository stockRepository;
	@Autowired
    CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = produitRepository.findAll();
		for (Produit produit : produits) {
			log.info(" Produit : " + produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p) {
		produitRepository.save(p);
		return p;
	}

	

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(Produit p) {
		produitRepository.save(p);
		p.setLibelleProduit("aa");
		return p;
		
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		log.info("produit :" + produit);
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		if (produit==(null))
		{
			log.info("Error");  
		}
		else
		{
			produit.setStock(stock);
			produitRepository.save(produit);
		
	}
		

	}	


}