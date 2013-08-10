<%@ page import="com.peladafc.struts.form.*, java.util.*, com.peladafc.bean.*" %>
<%@ include file="/pfcInclude.jsp"%>
<%
String action = (String)request.getAttribute("action");
boolean disabledDate = false;
if(action != null && action.equals("/adm/Jogo_u.do")){
	disabledDate = true;
}
%>
<script src="../js/verificaCampos.js"></script>
<table  border="0" cellpadding="0" cellspacing="0" height="18">
	<tr align="center">
		<td width="150" class="pastaDesat"><a href="Jogo_l.do">Lista de jogos </a></td>
		<td width="5" bgcolor="#FFFFFF">&nbsp;</td>
		<td width="150" class="pastaAtiva"><strong>Novo jogo </strong></td>
	</tr>
</table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="2" bgcolor="#CCCCCC"></td>
	</tr>
</table>
<br>
<html:form action="<%=action%>" method="POST" onsubmit="return verificarCampos('jogoForm')">
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
    	<tr bgcolor="#CCCCCC">
    		<td bgcolor="#efefef">Dados Principais </td>
   		</tr>
   	</table>
	<br>
    <table width="100%"  border="0" cellspacing="0" cellpadding="2">
    	<tr>
    		<td align="right">Ano : </td>
    		<td colspan="4">
    			<html:select name="jogoForm" property="jogo.ano" style="width:100% ">
    				<option value="2004">2004</option>
   				</html:select>
   			</td>
   		</tr>
    	<tr>
    		<td align="right">Quadrimestre: </td>
    		<td colspan="4">
    			<html:radio name="jogoForm" property="jogo.quadrimestre" value="1" onclick="verificaMultiplo(this)"/>
			1&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="jogoForm" property="jogo.quadrimestre" value="2" onclick="verificaMultiplo(this)"/>
			2 &nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="jogoForm" property="jogo.quadrimestre" value="3" onclick="verificaMultiplo(this)"/>
			3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
   		</tr>
    	<tr>
    		<td width="150" align="right">Data : </td>
    		<td colspan="4">
    			<html:text name="jogoForm" property="jogo.data" style="width:100% " readonly="<%=disabledDate%>" onclick="verificaData(this,1)" maxlength="10"/>
   			</td>
   		</tr>
    	<tr >
        	<td align="right">Gols do Time ${nmTimeA}:</td>
        	<td width="100"><html:text name="jogoForm" property="jogo.golsA" style="width:100% " onclick="verificaNumero(this,1)"/></td>
   		    <td width="150" align="right">Gols do Time ${nmTimeB}:</td>
   		    <td width="100"><html:text name="jogoForm" property="jogo.golsB" style="width:100% " onclick="verificaNumero(this,1)"/></td>
   		    <td>&nbsp;</td>
    	</tr>
    	<tr >
    		<td align="right">&nbsp;</td>
    		<td colspan="4"></td>
   		</tr>
   	</table>
    <br>
    <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
    	<tr bgcolor="#efefef">
    		<td bgcolor="#efefef">Apelido</td>
    		<td width="50" align="center" bgcolor="#efefef">N&atilde;o </td>
    		<td width="50" align="center" bgcolor="#efefef">Time ${nmTimeA}</td>
    		<td width="50" align="center" bgcolor="#efefef">Time ${nmTimeB}</td>
    		<td width="40" align="center" bgcolor="#efefef" style="display:none ">Gols</td>
   		    <td width="25" align="center" bgcolor="#efefef" title="Cart&otilde;es amarelos">N</td>
   		    <td width="25" align="center" bgcolor="#FFFF00" title="Cart&otilde;es amarelos">C</td>
   		    <td width="25" align="center" bgcolor="#66CCFF" title="Cart&otilde;es azuis">C</td>
   		    <td width="25" align="center" bgcolor="#FF7553" title="Cart&ocirc;es vermelhos">C</td>
	    </tr>
<logic:iterate name="jogoForm" property="jogo.jogadores" id="jogador">
    	<tr>
    		<td bgcolor="#FFFFFF">
    			<bean:write name="jogador" property="apelido"/>
    			<html:hidden name="jogador" property="apelido" indexed="true"/>
    		</td>
    		<td width="50" align="center" bgcolor="#FFFFFF">
    			<html:radio name="jogador" property="time" value="N" indexed="true"/>
   			</td>
    		<td width="50" align="center" bgcolor="#FFFFFF">
    			<html:radio name="jogador" property="time" value="A" indexed="true"/>
   			</td>
    		<td width="50" align="center" bgcolor="#FFFFFF">
    			<html:radio name="jogador" property="time" value="B" indexed="true"/>
   			</td>
    		<td width="40" align="center" bgcolor="#FFFFFF" style="display:none ">
    			<!--html:text name="jogador" property="gols" indexed="true" style="width:100%;text-align:center" /-->
   			</td>
   		    <td width="25" align="center" bgcolor="#FFFFFF">
   		    	<html:radio name="jogador" property="cartao" value="Nenhum" indexed="true"/>
			</td>
   		    <td width="25" align="center" bgcolor="#FFFFFF">
   		    	<html:radio name="jogador" property="cartao" value="Amarelo" indexed="true"/>
			</td>
   		    <td width="25" align="center" bgcolor="#FFFFFF">
   		    	<html:radio name="jogador" property="cartao" value="Azul" indexed="true"/>
			</td>
   		    <td width="25" align="center" bgcolor="#FFFFFF">
   		    	<html:radio name="jogador" property="cartao" value="Vermelho" indexed="true"/>
			</td>
	    </tr>
</logic:iterate>
   	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
		    <div align="right"><br>
		    	<input type="submit" name="Submit" value="Salvar">
		   	</div>
		</logic:equal>
	</logic:equal>
</html:form>
<br>
