package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Funcionario.
 * 
 * @author gabriel.santos
 */
public class Funcionario {

    /** The cpf. */
    @CPF(message = "O CPF do cliente está inválido", groups = { Put.class, Post.class })
    @NotBlank(message = "O campo CPF não pode estar nulo", groups = { Put.class, Post.class })
    private String cpf;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio", groups = { Put.class, Post.class })
    @Pattern(regexp = RegexType.NOME, message = "O nome do funcionário está incorreto", groups = { Put.class, Post.class })
    @Size(min = 2, max = 80, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres", groups = { Put.class, Post.class })
    private String nome;

    /** The idade. */
    @NotEmpty
    @Min(value = 1, message = "A idade do funcionario não pode ser menor que 1", groups = { Put.class, Post.class })
    private Integer idade;
    
    /** The salario. */
    @Min(value = 1, message = "O salário do funcionário não pode ser negativo", groups = { Put.class, Post.class })
    private BigDecimal salario;

    /** The data contratacao. */
    @NotNull(message = "A data de contratação do funcionario não deve estar nula", groups = { Put.class, Post.class })
    @Past(message = "A data de contratação não pode ser maior que a data atual", groups = { Put.class, Post.class })
    private LocalDate dataContratacao;

    /** The data salario. */
    @Future(message = "A data do salario deve ser maior que a data atual", groups = { Put.class, Post.class })
    @NotNull(message = "A data do salário do funcionario não deve estar nula", groups = { Put.class, Post.class })
    private LocalDate dataSalario;

    /** The telefones. */
    @Valid
    @NotNull(message = "O telefone do funcionario não pode ser vazio", groups = { Put.class, Post.class })
    @Size.List({@Size(min = 1, message = "A lista de telefone da empresa não deve ser vazio.", groups = { Put.class,Post.class }),
		@Size(max = 500, message = "A lista de telefone da empresa máxima é de {max}.", groups = { Put.class,Post.class }) })
    private Set<Telefone> telefones;

    /** The enderecos. */
    @Valid
    @NotNull(message = "O endereço do funcionário não pode estar vazio")
    @Size.List({@Size(min = 1, message = "A lista de telefone do funcionário mínima é de {min}.", groups = { Put.class,Post.class }),
		@Size(max = 5, message = "A lista de telefone do funcionário máxima é de {max}.", groups = { Put.class,Post.class }) })
    private Set<Endereco> enderecos;

    /**
     * Instantiates a new funcionario.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param salario the salario
     */
    public Funcionario(String cpf, String nome, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    /**
     * Instantiates a new funcionario.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param idade the idade
     * @param telefone the telefone
     * @param endereco the endereco
     * @param salario the salario
     */
    public Funcionario(String cpf, String nome, int idade, @Valid Set<Telefone> telefone, @Valid Set<Endereco> endereco, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.telefones = telefone;
        this.enderecos = endereco;
        this.salario = salario;
    }

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
    	if (salario.doubleValue() >= 0) {
    		this.salario = salario;
    	} else {
    		throw new IllegalArgumentException("salario não pode ser negativo");
    	}
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataPagamento) {
        this.dataContratacao = dataPagamento;
    }

    public LocalDate getDataSalario() {
        return dataSalario;
    }

    public void setDataSalario(LocalDate dataSalario) {
        this.dataSalario = dataSalario;
    }

    public void setTelefones(Set<Telefone> telefone) {
        this.telefones = telefone;
    }

    public void setEnderecos(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    
}
