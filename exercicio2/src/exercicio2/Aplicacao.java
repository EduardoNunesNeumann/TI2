package exercicio2;
import java.util.List;
import java.util.Scanner;



public class Aplicacao {
    public static void main(String[] args) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Menu ====");
            System.out.println("1. Inserir usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Excluir usuário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Lógica para inserir usuário
                    Usuario novoUsuario = lerDadosUsuario(scanner);
                    if (usuarioDAO.insert(novoUsuario)) {
                        System.out.println("Usuário inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir usuário.");
                    }
                    break;
                case 2:
                    // Lógica para listar usuários
                    List<Usuario> usuarios = usuarioDAO.get();
                    for (Usuario u : usuarios) {
                        System.out.println(u.toString());
                    }
                    break;
                case 3:
                    // Lógica para atualizar usuário
                    int codigoAtualizacao = lerCodigoUsuario(scanner);
                    Usuario usuarioAtualizado = usuarioDAO.get(codigoAtualizacao);
                    if (usuarioAtualizado != null) {
                        lerDadosAtualizacaoUsuario(scanner, usuarioAtualizado);
                        if (usuarioDAO.update(usuarioAtualizado)) {
                            System.out.println("Usuário atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar usuário.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4:
                    // Lógica para excluir usuário
                    int codigoExclusao = lerCodigoUsuario(scanner);
                    if (usuarioDAO.delete(codigoExclusao)) {
                        System.out.println("Usuário excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir usuário.");
                    }
                    break;
                case 5:
                    System.out.println("Encerrando aplicação. Até logo!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Usuario lerDadosUsuario(Scanner scanner) {
        System.out.print("Digite o código: ");
        int codigo = scanner.nextInt();
        System.out.print("Digite o login: ");
        String login = scanner.next();
        System.out.print("Digite a senha: ");
        String senha = scanner.next();
        System.out.print("Digite o sexo (M/F): ");
        char sexo = scanner.next().charAt(0);
        return new Usuario(codigo, login, senha, sexo);
    }

    private static int lerCodigoUsuario(Scanner scanner) {
        System.out.print("Digite o código do usuário: ");
        return scanner.nextInt();
    }

    private static void lerDadosAtualizacaoUsuario(Scanner scanner, Usuario usuario) {
        System.out.print("Digite o novo login: ");
        usuario.setLogin(scanner.next());
        System.out.print("Digite a nova senha: ");
        usuario.setSenha(scanner.next());
        System.out.print("Digite o novo sexo (M/F): ");
        usuario.setSexo(scanner.next().charAt(0));
    }
}
