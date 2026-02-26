"use server";

export async function signupServerAction(formData: FormData) {
  const payload = {
    userFullName: formData.get("userFullName"),
    mobile: formData.get("mobile"),
    email: formData.get("email"),
    password: formData.get("password"),
  };

  const response = await fetch("http://localhost:8080/api/auth/create", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    throw new Error("Signup failed");
  }

  return response.json();
}