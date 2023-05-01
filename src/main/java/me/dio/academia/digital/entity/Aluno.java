package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data //abstrai equal - hascode - get - set
@NoArgsConstructor ///cria construct empty
@AllArgsConstructor // cria construct com todos atributos
@Entity //persiste no BD, necessita de uma PrimaryKey
@Table(name = "tb_alunos") //determina que é tabela e nomeia
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})// hibernate terá incialização lenta
public class Aluno {

  @Id //Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true) // estabelece que é unico, é uma coluna con elementos unicos, não se repetem
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @ManyToOne
  private Endereco endereco;

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY) //quando relacionamos tabela devemos anotar | um aluno tem varias avaliações
  //Fetch Type.LAzy diz que a avalições serão criada em um endpoint a parte dos dados do aluno
  @JsonIgnore //elimina possiveis infinity looping
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

}
