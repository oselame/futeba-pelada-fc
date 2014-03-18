<!-- pfcJogosJogo.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<style type="text/css">
<!--
.placar {
	font-size: 14px;
	font-weight: bold;
	color: #006600;
}
-->
</style>
<p><strong>Jogo </strong></p>
<form name="partidaForm" method="post" action="">
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">Dados Principais</td>
		</tr>
	</table>
	<br>
	<table width="100%"  border="0" cellspacing="0" cellpadding="2">
		<tag:espacador />
		<tr>
			<td align="right">
				<b>Ano:</b>
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.nuAno"/>
			</td>
			<td align="right">
				<b>Quadrimestre:</b> 
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.cdQuadrimestre"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<b>Partida:</b>
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.partidaPK.cdPartida"/>
			</td>		
			<td align="right">
				<b>Data:</b>
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.dtPartidaformatada"/>
			</td>
		</tr>
	</table>
	<br>
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td width="30%"><strong>Time ${nmTimeA} </strong></td>
    		<td align="center" class="placar"><bean:write name="partidaForm" property="entidade.dePlacar"/></td>
    		<td width="30%" align="right"><strong>Time ${nmTimeB} </strong></td>
   		</tr>
    </table>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td width="48%" valign="top">
    			<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                	<tr bgcolor="#efefef">
                		<td>Apelido</td>
                		<td width="30" align="center">Gols</td>
                		<td width="40" align="center">Contra</td>
                		<td width="60" align="center" colspan="3">Cart&otilde;es</td>
           		    </tr>
           			<logic:iterate name="partidaForm" property="entidade.sociosPartida" id="row">
           				<logic:equal name="row" property="cdTime" value="1">
		                	<tr>
		                		<td bgcolor="#FFFFFF">
		                			<bean:write name="row" property="socio.nmApelido" />
		                			<%--
		                			<logic:equal name="row" property="socio.flForauso" value="1">
		                				(desativado)
		                			</logic:equal>
		                			--%>
		                		</td>
		                		<td align="center" bgcolor="#FFFFFF">
		                			<bean:write name="row" property="nuGol" />
		                		</td>
		                		<td align="center" bgcolor="#FFFFFF">
		                			<bean:write name="row" property="nuGolcontra" />
		                		</td>		                		
                				<td align="center" bgcolor="${row.flCartaovermelho eq 1 ? '#FF0000' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
                				<td align="center" bgcolor="${row.flCartaoazul eq 1 ? '#0000FF' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
		                		<td align="center" bgcolor="${row.flCartaoamarelo eq 1 ? '#FFFF00' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
		           		    </tr>
           		    	</logic:equal>
           		    </logic:iterate>
                </table>
    		</td>
    		<td width="4%">&nbsp;</td>
    		<td width="48%" valign="top">   			
    			<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                	<tr bgcolor="#efefef">
                		<td>Apelido</td>
                		<td width="30" align="center">Gols</td>
                		<td width="40" align="center">Contra</td>
                		<td width="60" align="center" colspan="3">Cart&otilde;es</td>
               		</tr>
           			<logic:iterate name="partidaForm" property="entidade.sociosPartida" id="row">
           				<logic:equal name="row" property="cdTime" value="2">
		                	<tr>
		                		<td bgcolor="#FFFFFF">
		                			<bean:write name="row" property="socio.nmApelido" />
		                			<%--
		                			<logic:equal name="row" property="socio.flForauso" value="1">
		                				(desativado)
		                			</logic:equal>	
		                			 --%>	                			
		                		</td>
		                		<td align="center" bgcolor="#FFFFFF">
		                			<bean:write name="row" property="nuGol" />
		                		</td>
		                		<td align="center" bgcolor="#FFFFFF">
		                			<bean:write name="row" property="nuGolcontra" />
		                		</td>		                		
                				<td align="center" bgcolor="${row.flCartaovermelho eq 1 ? '#FF0000' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
                				<td align="center" bgcolor="${row.flCartaoazul eq 1 ? '#0000FF' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
		                		<td align="center" bgcolor="${row.flCartaoamarelo eq 1 ? '#FFFF00' : '#FFFFFF'}" width="20">
                					&nbsp;
                				</td>
		           		    </tr>
           		    	</logic:equal>
           		    </logic:iterate>                
           		</table>
    		</td>
   		</tr>
   		</table>
   		<br>
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="48%"><img src="/imagens/espacador.gif" height="1px"></td>
				<td><img src="/imagens/espacador.gif" height="1px"></td>
				<td width="48%"><img src="/imagens/espacador.gif" height="1px"></td>
			</tr>
				<tr>
					<td valign="top">
							<table border="0" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="20px" height="35px">
									<img src="../imagens/icobolacheia.gif">
								</td>
								<td>
									<b>Bola Cheia</b>
								</td>
							</tr>
							<tr>
								<td width="20">
									&nbsp;
								</td>
								<td>
									${partidaForm.entidade.deBolacheia}
								</td>
							</tr>	   
							</table>	
					</td>
					<td><img src="/imagens/espacador.gif" height="1px"></td>
					<td valign="top">
							<table border="0" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="20px" height="35px">
									<img src="../imagens/icobolamurcha.jpg">
								</td>
								<td>
									<b>Bola Murcha</b>
								</td>
							</tr>
							<tr>
								<td width="20">
									&nbsp;
								</td>
								<td>
									${partidaForm.entidade.deBolamurcha}
								</td>
							</tr>	   
							</table>
					</td>
				</tr>
			
				<tr>
					<td><img src="/imagens/espacador.gif" height="1px">&nbsp;<br><br></td>
					<td><img src="/imagens/espacador.gif" height="1px"></td>
					<td><img src="/imagens/espacador.gif" height="1px"></td>
				</tr> 	   					
				<tr>
					<td colspan="3">
						<b>Observação</b>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<bean:write name="partidaForm" property="entidade.deObservacao" />
					</td>
				</tr>   		
		</table>
 </form>
<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<html:button value="Editar" property="bntEditar" styleClass="bN" 
				onclick="location.href='editarCadPartida.do?cdPartida=${partidaForm.entidade.partidaPK.cdPartida}'" />
		</logic:equal>
	</logic:equal>
</p> 
