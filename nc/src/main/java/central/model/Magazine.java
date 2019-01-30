package central.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import central.model.MagazinePaymentType;

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
    
    

}
