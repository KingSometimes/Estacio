/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cadastroee.controler;

import cadastroee.model.MovimentosVenda;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ramon
 */
@Local
public interface MovimentosVendaFacadeLocal {

    void create(MovimentosVenda movimentosVenda);

    void edit(MovimentosVenda movimentosVenda);

    void remove(MovimentosVenda movimentosVenda);

    MovimentosVenda find(Object id);

    List<MovimentosVenda> findAll();

    List<MovimentosVenda> findRange(int[] range);

    int count();
    
}
