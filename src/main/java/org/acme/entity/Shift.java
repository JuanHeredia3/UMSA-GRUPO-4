package org.acme.entity;



import java.time.LocalTime;
import java.util.Date;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "shifts")
public class Shift extends PanacheEntityBase{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long shiftId;
	
	@Column(name = "pacientName")
	private String pacientName;
	
	@Column(name = "shiftDate")
	@Temporal(TemporalType.DATE)
	private Date shiftDate;
	
	@Column(name = "startTime")
	@Temporal(TemporalType.TIME)
	private LocalTime startTime;
	
	@Column(name = "endTime")
	@Temporal(TemporalType.TIME)
	private LocalTime endTime;
	
	@Column(name = "consultation")
	private String consultation;
	
	@ManyToOne
	private MedicSpecialist medicSpecialist;

	
	
	public Shift() {
		super();
	}

	public Shift(Long shiftId, String pacientName, Date shiftDate, LocalTime startTime, LocalTime endTime,
			String consultation, MedicSpecialist medicSpecialist) {
		super();
		this.shiftId = shiftId;
		this.pacientName = pacientName;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.consultation = consultation;
		this.medicSpecialist = medicSpecialist;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public String getPacientName() {
		return pacientName;
	}

	public void setPacientName(String pacientName) {
		this.pacientName = pacientName;
	}

	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
	}

	public MedicSpecialist getMedicSpecialist() {
		return medicSpecialist;
	}

	public void setMedicSpecialist(MedicSpecialist medicSpecialist) {
		this.medicSpecialist = medicSpecialist;
	}
	
}
