package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanParametreNotificationCible {

	private final int referenceBPartnerId;
    private final int emetteurBPartnerId;
    private final Timestamp dateReference;

    public BeanParametreNotificationCible(
            int referenceBPartnerId,
            int emetteurBPartnerId,
            Timestamp dateReference
    ) {
        this.referenceBPartnerId = referenceBPartnerId;
        this.emetteurBPartnerId = emetteurBPartnerId;
        this.dateReference = dateReference;
    }

	public int getReferenceBPartnerId() {
		return referenceBPartnerId;
	}

	public int getEmetteurBPartnerId() {
		return emetteurBPartnerId;
	}

	public Timestamp getDateReference() {
		return dateReference;
	}


}
