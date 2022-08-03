package jCodeLibrary;

public class SessionManager
{
    /**
     * Команда выключения компьютера
     */
    public static final String ShutdownCommand = "shutdown -h ";
    
    /**
     * Команда перезагрузки компьютера
     */
    public static final String RestartCommand  = "shutdown -r ";
    
    /**
     * NT- Выключить компьютер, находящийся в интерактивном сеансе пользователя.
     * 
     * @param args
     *            Строка дополнительных аргументов
     * @throws Exception
     *             Ошибка при исполнении функции.
     * 
     * Пример: <code>
     * Выключить компьютер через 30 секунд.
     * Shutdown("30");
     * немедленно завершаем работу программы, так как компьютер будет выключаться.
     * System.exit(0);//Код результата программы
     * </code> Пример: <code>
     * Выключить компьютер немедленно:
     * Shutdown(null);
     * System.exit(0);
     * </code>
     */
    public static void Shutdown(String args) throws Exception
    {
        String s = null;
        if (StringOperations.StringIsNullOrEmpty(args))
            s = ShutdownCommand + "now";
        else
            s = ShutdownCommand + args;
        
        Runtime.getRuntime().exec(s.trim());
        
        // System.exit(0);
        return;
    }
    
    /**
     * NT- Перезагрузить компьютер, находящийся в интерактивном сеансе
     * пользователя.
     * 
     * @param args
     *            Строка дополнительных аргументов
     * @throws Exception
     *             Ошибка при исполнении функции.
     * 
     *             Пример: <code>
     * Перезагрузить компьютер через 30 секунд.
     * Restart("30");
     * немедленно завершаем работу программы, так как компьютер будет выключаться.
     * System.exit(0);//Код результата программы
     * </code> Пример: <code>
     * Перезагрузить компьютер немедленно:
     * Restart(null);
     * System.exit(0);
     * </code>
     */
    public static void Restart(String args) throws Exception
    {
        String s = null;
        if (StringOperations.StringIsNullOrEmpty(args))
            s = RestartCommand + "now";
        else
            s = RestartCommand + args;
        
        Runtime.getRuntime().exec(s.trim());
        
        // System.exit(0);
        return;
    }
    
}
