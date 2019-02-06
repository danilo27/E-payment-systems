package pc.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Merchant_Info")
public class MerchantInfo implements Serializable{
 
	
	 @EmbeddedId
	 private PaymentFieldId paymentFieldId;
	
	 @MapsId("Name")
	 @ManyToOne(optional = false)
	 @JoinColumn(name = "name", referencedColumnName = "name")
	 private PaymentType paymentType;
	 
	 @MapsId("MerchantId")
	 @ManyToOne(optional = false)
	 @JoinColumn(name = "merchantId", referencedColumnName = "merchantId")
	 private Merchant merchant;
	 
	 @MapsId("FieldName")
	 @ManyToOne (optional = false)
	 @JoinColumns ({
         @JoinColumn(name="fieldName", referencedColumnName = "fieldName"),
         @JoinColumn(name="name", referencedColumnName = "name"),
	 })
	 private PaymentTypeField paymentTypeField;

	 private String value;
	 
	public MerchantInfo(){}
	 
	public PaymentFieldId getPaymentFieldId() {
		return paymentFieldId;
	}

	public void setPaymentFieldId(PaymentFieldId paymentFieldId) {
		this.paymentFieldId = paymentFieldId;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public PaymentTypeField getPaymentTypeField() {
		return paymentTypeField;
	}

	public void setPaymentTypeField(PaymentTypeField paymentTypeField) {
		this.paymentTypeField = paymentTypeField;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MerchantInfo(PaymentFieldId paymentFieldId, PaymentType paymentType, Merchant merchant,
			PaymentTypeField paymentTypeField, String value) {
		super();
		this.paymentFieldId = paymentFieldId;
		this.paymentType = paymentType;
		this.merchant = merchant;
		this.paymentTypeField = paymentTypeField;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
		result = prime * result + ((paymentFieldId == null) ? 0 : paymentFieldId.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((paymentTypeField == null) ? 0 : paymentTypeField.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MerchantInfo other = (MerchantInfo) obj;
		if (merchant == null) {
			if (other.merchant != null)
				return false;
		} else if (!merchant.equals(other.merchant))
			return false;
		if (paymentFieldId == null) {
			if (other.paymentFieldId != null)
				return false;
		} else if (!paymentFieldId.equals(other.paymentFieldId))
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (paymentTypeField == null) {
			if (other.paymentTypeField != null)
				return false;
		} else if (!paymentTypeField.equals(other.paymentTypeField))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MerchantInfo [paymentFieldId=" + paymentFieldId + ", paymentType=" + paymentType + ", merchant="
				+ merchant + ", paymentTypeField=" + paymentTypeField + ", value=" + value + "]";
	}

	 
	 
	 
	
	
}
