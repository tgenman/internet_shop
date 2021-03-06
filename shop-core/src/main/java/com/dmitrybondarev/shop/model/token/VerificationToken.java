package com.dmitrybondarev.shop.model.token;

import com.dmitrybondarev.shop.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity(name = "VerificationToken")
@Table(name = "verification_token")
public class VerificationToken implements Serializable {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "user_id")
//            foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
    private User user;

    private String token;

    private Date expiryDate;

    public VerificationToken() {
        super();
    }

    public VerificationToken(final String token) {
        super();
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void setExpiryDate(final Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
