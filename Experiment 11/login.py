students = []

def validate_login(username, password):
    users = {"admin": "admin123", "student": "stud@1"}

    if username in users:
        if users[username] == password:
            return "Valid Login"

    return "Invalid Login"


def add_student(id, name, marks):
    students.append((id, name, marks))
    return True
