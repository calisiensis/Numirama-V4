package pl.org.schroeder.numirama.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "moneta")
public class Coin {

	private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "krajEmitent")
	@NotNull(message = "uzupełnij pole")
	@Pattern(regexp = "^[a-żA-Ż ]+{1,45}$", message = "za długa nazwa, max to 45 znaków albo w nazwie jest cyfra")
	private String country;

	@Column(name = "nazwaWaluty")
	@NotNull(message = "uzupełnij pole")
	@Pattern(regexp = "^[a-żA-Ż ]+{1,45}$", message = "za długa nazwa, max to 45 znaków albo w nazwie jest cyfra lub inny niedozwolony znak")
	private String currency;

	@Column(name = "nominalWaluty")
	@NotNull(message = "uzupełnij pole")
	@DecimalMin(value = "0.01", inclusive = true, message = "wartość nie może być zerowa lub ujemna")
	@DecimalMax(value = "9999999999.99", inclusive = true, message = "wartość nie może wynosić więcej niż 9 999 999 999.9")
	@Positive(message = "nie ma roku zerowego")
	private BigDecimal nominal;

	@Column(name = "rokWybicia")
	@NotNull(message = "uzupełnij pole, wpisz 0000 jeśli rok jest nieznany")
	@Range(min = -1000, max = 2019, message = "nie ma monet starszych niż 1000 pne i 2019 ne")
	private Integer year;

	@Column(name = "material")
	@NotNull(message = "uzupełnij pole")
	@Pattern(regexp = "^[a-żA-Ż0-9 ]+{1,45}$", message = "za długa nazwa, max to 45 znaków albo w nazwie jest niedozwolony znak")
	private String material;

	@Column(name = "srednica")
	@DecimalMin(value = "0.01", inclusive = true, message = "wartość nie może być zerowa lub ujemna")
	@DecimalMax(value = "1000.00", inclusive = true, message = "wartość nie może wynosić więcej niż 1 metr")
	private BigDecimal radius;

	@Column(name = "waga")
	@DecimalMin(value = "0.01", inclusive = true, message = "wartość nie może być zerowa lub ujemna")
	@DecimalMax(value = "1000.00", inclusive = true, message = "wartość nie może wynosić więcej niż 1kg")
	private BigDecimal weight;

	@Column(name = "stan")
	@Pattern(regexp = "^[a-żA-Ż0-9!@#$%^&*()-_+=? ]+{1,45}", message = "za długa nazwa, max to 45 znaków")
	private String state;

	@Column(name = "uwagi")
	@Pattern(regexp = "^[a-żA-Ż0-9!@#$%^&*()-_+=? ]+{1,45}", message = "za długa nazwa, max to 45 znaków")
	private String comments;

	@Column(name = "cena")
	@DecimalMin(value = "0.00", inclusive = true, message = "wartość nie może być zerowa lub ujemna")
	@DecimalMax(value = "9999999999.99", inclusive = true, message = "wartość nie może wynosić więcej niż 9 999 999 999.9")
	private BigDecimal price;

	@Column(name = "status")
	@Pattern(regexp = "^[a-żA-Ż0-9!@#$%^&*()-_+=? ]+{1,45}", message = "za długa nazwa, max to 45 znaków")
	private String status;

	public Coin() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getNominal() {
		return nominal;
	}

	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public BigDecimal getRadius() {
		return radius;
	}

	public void setRadius(BigDecimal radius) {
		this.radius = radius;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Coin [id=" + id + ", country=" + country + ", currency=" + currency + ", nominal=" + nominal + ", year="
				+ year + ", material=" + material + ", radius=" + radius + ", weight=" + weight + ", state=" + state
				+ ", comments=" + comments + ", price=" + price + ", status=" + status + "]";
	}

}
