"use client";

import { useRouter } from "next/navigation";
import { JSX } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { toast } from "sonner";

type SignupForm = {
  userFullName: string;
  mobile: string;
  email: string;
  password: string;
};

export default function SignupPage(): JSX.Element {
  const { register, handleSubmit } = useForm<SignupForm>();
  const router = useRouter();

  const onSubmit: SubmitHandler<SignupForm> = async (data) => {
    try {
      const res = await fetch("http://localhost:8080/api/auth/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(data),
      });

      if (!res.ok) {
        throw new Error("Signup failed");
      }

      toast.success("Signup successful. Please login.");
      router.push("/login");
    } catch (err) {
      console.error(err);
      toast.error("Signup failed");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-[#0b0f14] to-[#1a1f26] px-6">
      <div className="w-full max-w-6xl bg-[#0f131a] rounded-2xl shadow-2xl grid grid-cols-1 md:grid-cols-2 overflow-hidden">

        {/* LEFT INFO */}
        <div className="p-10 text-white">
          <p className="text-sm text-gray-400 mb-2">● Support</p>
          <h1 className="text-3xl font-bold mb-6">
            Share your daily experience here
          </h1>
        </div>

        {/* RIGHT FORM */}
        <div className="bg-[#121720] p-10">
          <h2 className="text-white text-xl font-semibold mb-6">
            Register with
          </h2>

          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <input
                placeholder="Full name"
                className="input-dark"
                {...register("userFullName", { required: true })}
              />
              <input
                placeholder="Mobile number"
                className="input-dark"
                {...register("mobile", { required: true })}
              />
            </div>

            <input
              type="email"
              placeholder="Email"
              className="input-dark"
              {...register("email", { required: true })}
            />

            <input
              type="password"
              placeholder="Password"
              className="input-dark"
              {...register("password", { required: true })}
            />

            <button
              type="submit"
              className="w-full bg-lime-400 hover:bg-lime-500 text-black py-2 rounded-lg font-semibold transition"
            >
              Sign up
            </button>

            <p className="text-sm text-gray-400 text-center">
              Already have an account?{" "}
              <a href="/login" className="text-white underline">
                Sign in
              </a>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}