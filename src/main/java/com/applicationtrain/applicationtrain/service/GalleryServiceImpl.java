package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> usersByGenderM(){
        List<User> usersHommes = userRepository.findMaleByTrainNumber();
        return usersHommes;
    }
    @Override
    public List<User> usersByGenderF(Long id) {
        // recupérer l'utilisateur connecté
        Optional<User> female = userRepository.findById(id);
        // récupérer son numero de train
        List<User> usersFemmes = userRepository.findFemaleByTrainNumber(female.get().getTrain_number());
        return usersFemmes;
    }
}


    /*@Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }
    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {*/