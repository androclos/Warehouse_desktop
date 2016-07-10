package Helper;

/**
 *Usert reprezentalo objektum
 */
public class UserContainer {
    
    private int id;
    private String email;
    private String username;
    private String name;
    private String bday;
    private String salt;
    private String password;
    private String role;



    public UserContainer(int id, String username, String name, String bday, String salt, String password, String role, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.bday = bday;
        this.salt = salt;
        this.password = password;
        this.role = role;
        this.email = email;
    }


    public UserContainer (UserContainer c){
    
        this.id = c.id;
        this.username = c.username;
        this.name =  c.name;
        this.bday = c.bday;
        this.salt = c.salt;
        this.password = c.password;
        this.role = c.role;
        this.email = c.email;
    }
    
    @Override
    public String toString(){

        return username+"-"+name+"-"+bday+"-"+salt+"-"+password+"-"+role+"-"+email;  

    }
    
    @Override
    public int hashCode(){
        
        return id*username.hashCode()*name.hashCode()*bday.hashCode()*salt.hashCode()*password.hashCode()*role.hashCode()*email.hashCode();  
    
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserContainer other = (UserContainer) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.bday == null) ? (other.bday != null) : !this.bday.equals(other.bday)) {
            return false;
        }
        if ((this.salt == null) ? (other.salt != null) : !this.salt.equals(other.salt)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.role == null) ? (other.role != null) : !this.role.equals(other.role)) {
            return false;
        }
        return true;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getSlat() {
        return salt;
    }

    public void setSlat(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
