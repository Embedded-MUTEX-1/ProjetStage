package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.EmailDetails;

public interface EmailService {
    public void sendSimpleMail(EmailDetails details);

}