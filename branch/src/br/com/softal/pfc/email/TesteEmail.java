package br.com.softal.pfc.email;

public class TesteEmail {
	protected void enviar() {
		SendEmail2 email = new SendEmail2();
		email.setFrom("adrianooselame@gmail.com");
		email.setSubject("Teste Teste Teste");
		email.addRecipient("adrianooselame@gmail.com");
		email.setCorpoMsg("XXXXXX");
		email.enviarEmail();
	}
	
	public static void main(String[] args) {
		TesteEmail t = new TesteEmail();
		t.enviar();
	}

}
