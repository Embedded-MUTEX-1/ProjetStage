package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepo utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Utilisateur utilisateur = utilisateurRepo.findUtilisateurByEmail(username).get();
            return utilisateur;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Could not find user");
        }
    }
}
