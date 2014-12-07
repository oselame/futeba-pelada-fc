<%@ include file="/pfcInclude.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolorz="#efefef">
<tr>
	<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="3" align="center"><b>${partida.dePlacar}</b></td>
		</tr>
		<tr>
			<td width="50%" valign="top" align="right">										
				<b>Time ${nmTimeA}</b>		
				<table width="100%" border="0" cellspacing="0" cellpadding="0">																														
					<logic:iterate name="jogo" id="row">
						<logic:equal name="row" property="cdTime" value="1">																
							<tr>
								<td align="right">
		                			<logic:greaterThan name="row" property="nuGol" value="0">
		                			 (<bean:write name="row" property="nuGol" />)
		                			</logic:greaterThan>
		                			<logic:greaterThan name="row" property="nuGolcontra" value="0">
		                				<font color="red">(-<bean:write name="row" property="nuGolcontra" />)</font>
		                			</logic:greaterThan>		                			
									<bean:write name="row" property="socio.nmApelido" />
								</td>
							</tr>
						</logic:equal>
					</logic:iterate>
				</table>
			</td>
			<td width="4">&nbsp;&nbsp;</td>
			<td width="50%" valign="top">
				<b>Time ${nmTimeB}</b><br>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<logic:iterate name="jogo" id="row">
						<logic:equal name="row" property="cdTime" value="2">																
							<tr>
								<td>
									<bean:write name="row" property="socio.nmApelido" />
		                			<logic:greaterThan name="row" property="nuGol" value="0">
		                			 (<bean:write name="row" property="nuGol" />)
		                			</logic:greaterThan>
		                			<logic:greaterThan name="row" property="nuGolcontra" value="0">
		                				<font color="red">(-<bean:write name="row" property="nuGolcontra" />)</font>
		                			</logic:greaterThan>		                			
								</td>
							</tr>
						</logic:equal>
					</logic:iterate>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" align="left" valign="middle">
				<table width="100%" cellpadding="0" cellspacing="2" border="0" >
					<tr>
						<td valign="top" width="20"><img src="../imagens/icobolacheia.gif"></td>
						<td>${partida.deBolacheia}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" width="20"><img src="../imagens/icobolamurcha.jpg"></td>
						<td>${partida.deBolamurcha}</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
</tr>
</table>