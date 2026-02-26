"use server"

const loginServerAction = async (formData: FormData) =>
{
  const payload = {
    email: formData.get("email"),
    password: formData.get("password"),
    remember: formData.get("remember") === "true",
  };


  const response = await fetch("http://localhost:8080/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  });       

  };

  export default loginServerAction;