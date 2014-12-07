package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.dto.EmailAtrasoDto;
import br.com.softal.pfc.dto.RelatorioDto;
 
public interface SociopartidaDAO extends DAO {
	
	@SuppressWarnings("unchecked")
	List findAllJogodoresTimes(Integer cdPartida) throws DAOException;
	Boolean existeParticipantes(Integer cdPartida) throws DAOException;
	List<RelatorioDto> findAllSociosAtrazados(RelatorioDto relatorioDto) throws DAOException;
	Boolean existeAtrasadosOuCartoes(Integer cdPartida) throws DAOException;
	List<EmailAtrasoDto> findAllSociosAtrasadosComCartao(Integer cdPartida) throws DAOException;
	void deleteSociospartida(Integer cdPartida) throws DAOException;
 
}
