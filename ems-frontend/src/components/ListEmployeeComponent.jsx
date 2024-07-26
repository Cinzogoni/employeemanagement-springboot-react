import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

import "../components/ListEmployeeComponent.css";

import { listEmployee } from "../services/EmployeeService";
import { deleteEmployee } from "../services/EmployeeService";

function ListEmployeeComponent() {
  const [employees, setEmployees] = useState([]);

  const navigator = useNavigate();

  useEffect(() => {
    getAllEmployee();
  }, []);

  function getAllEmployee() {
    listEmployee()
      .then((response) => {
        setEmployees(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function addNewComponent() {
    navigator("/add-employee");
  }

  function updateEmployee(id) {
    navigator(`/update-employee/${id}`);
  }

  function removeEmployee(id) {
    if (window.confirm("Are you sure you want to delete this employee?")) {
      deleteEmployee(id)
        .then((response) => {
          getAllEmployee(response);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }

  return (
    <div className="container">
      <h1>List Of Employees</h1>
      <button onClick={addNewComponent} className="btn btn-primary mb-2">
        Add Component
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Employee Id</th>
            <th>Employee First Name</th>
            <th>Employee Last Name</th>
            <th>Employee Email Id</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateEmployee(employee.id)}
                >
                  Update
                </button>

                <button
                  className="btn btn-danger"
                  onClick={() => removeEmployee(employee.id)}
                  style={{ marginLeft: "15px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ListEmployeeComponent;
