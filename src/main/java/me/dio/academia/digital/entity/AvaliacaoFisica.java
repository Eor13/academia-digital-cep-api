package me.dio.academia.digital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data //abstrai equal - hascode - get - set
@NoArgsConstructor ///cria construct empty
@AllArgsConstructor // cria construct com todos atributos
@Entity //persiste no BD, necessita de uma PrimaryKey
@Table(name = "tb_avaliacoes") //determina que é tabela e nomeia
public class AvaliacaoFisica {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL) //tudo que fizer em avaliação fisica reflete em aluno
  @JoinColumn(name = "aluno_id")// a coluna estará dentro da tabela avaliação fisica
  private Aluno aluno;

  private LocalDateTime dataDaAvaliacao = LocalDateTime.now();

  @Column(name = "peso_atual")
  private double peso;

  @Column(name = "altura_atual")
  private double altura;

}
