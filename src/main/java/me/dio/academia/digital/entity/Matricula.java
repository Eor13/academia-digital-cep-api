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
@Table(name = "tb_matriculas") //determina que é tabela e nomeia
public class Matricula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)  // só tem uma matricula para cada aluno e vice-versa
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  private LocalDateTime dataDaMatricula = LocalDateTime.now();
}
