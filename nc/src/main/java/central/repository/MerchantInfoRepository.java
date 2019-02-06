package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import central.model.MerchantInfo;

@Repository
public interface MerchantInfoRepository extends JpaRepository<MerchantInfo, Long>{
	@Query("select i from MerchantInfo i where i.paymentFieldId.name = ?1 and i.paymentFieldId.merchantId = ?2 and i.paymentFieldId.fieldName = ?3")
	public MerchantInfo findMerchantData(String paymentTypeName, String merchantId, String fieldName);
}
