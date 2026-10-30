import pytest
from login import validate_login


def test_valid_login():
    assert validate_login("admin", "admin123") == "Valid Login"


def test_wrong_password():
    assert validate_login("admin", "wrong") == "Invalid Login"


def test_unknown_user():
    assert validate_login("xyz", "123") == "Invalid Login"


def test_empty_username():
    assert validate_login("", "admin123") == "Invalid Login"
