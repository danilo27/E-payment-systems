package central.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "MAGAZINE")
public class Magazine {
	@Id
    @Column(name = "ISSN", length = 8)
    private String issn;

    @Column(name = "NAME", length = 120, nullable = false)
    private String name;
 
    @Column(name = "PAYMENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private MagazinePaymentType paymentType;

    @Column(name = "MEMBERSHIP_PRICE", nullable = true)
    private Double membershipPrice;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
   // @JsonIgnoreProperties("magazine")
    private List<Issue> issues = new ArrayList<Issue>();
    
//    @JsonIgnoreProperties("magazine")
    @JsonIgnore
    @OneToOne// ( optional = false, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Merchant merchant;
    
    public Magazine(){}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MagazinePaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(MagazinePaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Double getMembershipPrice() {
		return membershipPrice;
	}

	public void setMembershipPrice(Double membershipPrice) {
		this.membershipPrice = membershipPrice;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
/*
	@Override
	public String toString() {
		return "Magazine [issn=" + issn + ", name=" + name + ", paymentType=" + paymentType + ", membershipPrice="
				+ membershipPrice + ", issues=" + issues + ", merchant=" + merchant + "]";
	}*/
    
    

}
