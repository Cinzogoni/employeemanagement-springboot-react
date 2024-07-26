import React, { useEffect } from "react";
import { useState } from "react";
import {
  createEmployee,
  getEmployee,
  updateEmployee,
} from "../services/EmployeeService";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";

function EmployeeComponent() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const { id } = useParams();

  const [blankError, setBlankError] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });

  const navigator = useNavigate();

  useEffect(() => {
    if (id) {
      getEmployee(id)
        .then((response) => {
          setFirstName(response.data.firstName);
          setLastName(response.data.lastName);
          setEmail(response.data.email);
        })
        .catch((error) => console.error(error));
    }
  }, []);

  function saveOrUpdateEmployee(e) {
    e.preventDefault();

    if (validateForm()) {
      const employee = { firstName, lastName, email };
      console.log(employee);

      if (id) {
        updateEmployee(id, employee)
          .then((response) => {
            console.log(response.data);
            navigator("/employees");
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        createEmployee(employee)
          .then((response) => {
            console.log(response.data);
            navigator("/employees");
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
  }

  function validateForm() {
    let valid = true;
    const validBlankErrors = { ...blankError };

    if (firstName.trim()) {
      validBlankErrors.firstName = "";
    } else {
      validBlankErrors.firstName = "First name is required";
      valid = false;
    }

    if (lastName.trim()) {
      validBlankErrors.lastName = "";
    } else {
      validBlankErrors.lastName = "Last name is required";
      valid = false;
    }

    if (email.trim()) {
      validBlankErrors.email = "";
    } else {
      validBlankErrors.email = "Email is required";
      valid = false;
    }
    setBlankError(validBlankErrors);
    return valid;
  }

  function handleInputChange(e) {
    const { name, value } = e.target;

    if (name === "firstName") setFirstName(value);
    if (name === "lastName") setLastName(value);
    if (name === "email") setEmail(value);

    setBlankError((prevErrors) => ({
      ...prevErrors,
      [name]: value.trim()
        ? ""
        : `${name.charAt(0).toUpperCase() + name.slice(1)} is required`,
    }));
  }

  function pageTitle() {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>;
    } else {
      return <h2 className="text-center">Add Employee</h2>;
    }
  }

  return (
    <div className="container">
      <br></br>
      <br></br>
      <br></br>
      <div className="row">
        <div className="card col-md-6 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">First Name: </label>
                <input
                  className={`form-control ${
                    blankError.firstName ? `is-invalid` : ``
                  }`}
                  type="text"
                  placeholder="Enter Employee First Name"
                  name="firstName"
                  value={firstName}
                  onChange={handleInputChange}
                />
                {blankError.firstName && (
                  <div className="invalid-feedback">{blankError.firstName}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Last Name: </label>
                <input
                  className={`form-control ${
                    blankError.lastName ? `is-invalid` : ``
                  }`}
                  type="text"
                  placeholder="Enter Employee Last Name"
                  name="lastName"
                  value={lastName}
                  onChange={handleInputChange}
                />
                {blankError.lastName && (
                  <div className="invalid-feedback">{blankError.lastName}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Email: </label>
                <input
                  className={`form-control ${
                    blankError.email ? `is-invalid` : ``
                  }`}
                  type="text"
                  placeholder="Enter Employee Email"
                  name="email"
                  value={email}
                  onChange={handleInputChange}
                />
                {blankError.email && (
                  <div className="invalid-feedback">{blankError.email}</div>
                )}
              </div>

              <button
                className="btn btn-success"
                onClick={saveOrUpdateEmployee}
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EmployeeComponent;
