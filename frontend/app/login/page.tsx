"use client";

import { useForm } from "react-hook-form";
import Image from "next/image";
import Link from "next/link";
import { log } from "console";
import loginServerAction from "@/ServerActions/loginServerAction";
import { toast } from "sonner";
import { useRouter } from "next/navigation";

type LoginForm = {
  email: string;
  password: string;
  remember: boolean;
};

export default function LoginPage()
{
  const { register, handleSubmit } = useForm<LoginForm>();

  const router = useRouter();

  const onSubmit = async (data: LoginForm) => {
    try {
      const res = await fetch("http://localhost:8080/api/auth/login",
         {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify(data),
      });

      if (!res.ok) throw new Error("Login failed");

      const result = await res.json();

      document.cookie = `token=${result.token}; path=/`;

      toast.success("Login successful");
      router.push("/dashboard");
    } catch (err) {
      toast.error("Invalid credentials");
    }
  };



return (
  <div className="min-h-screen flex bg-[#0f0f0f]">
    {/* LEFT FORM */}
    <div className="w-full md:w-1/2 flex items-center justify-center">
      <div className="w-full max-w-md bg-[#151515] rounded-xl p-8 shadow-xl">
        <h1 className="text-3xl font-semibold text-white mb-1">
          Welcome back
        </h1>
        <p className="text-gray-400 mb-6">
          Please enter your details
        </p>

        {/* Google Button */}
        <button className="w-full flex items-center justify-center gap-2 bg-white text-black py-2 rounded-lg font-medium hover:bg-gray-200 transition">
          <span className="text-lg">G</span>
          Sign in with Google
        </button>

        <div className="flex items-center my-6">
          <div className="flex-1 h-px bg-gray-700" />
          <span className="px-4 text-gray-400 text-sm">or</span>
          <div className="flex-1 h-px bg-gray-700" />
        </div>

        {/* FORM */}
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="label">Email address</label>
            <input
              type="email"
              className="input"
              {...register("email")}
            />
          </div>

          <div>
            <label className="label">Password</label>
            <input
              type="password"
              className="input"
              {...register("password")}
            />
          </div>

          <div className="flex items-center justify-between text-sm text-gray-400">
            <label className="flex items-center gap-2">
              <input type="checkbox" {...register("remember")} />
              Remember for 30 days
            </label>
            <a className="text-blue-500 hover:underline cursor-pointer">
              Forgot password
            </a>
          </div>

          <button className="w-full bg-white text-black py-2 rounded-lg font-medium hover:bg-gray-200 transition">
            Sign in
          </button>
        </form>

        <p className="text-center text-gray-400 text-sm mt-6">
          Don’t have an account?{" "}
          <Link href="/signup" className="text-blue-500 hover:underline">Sign up</Link>
        </p>
      </div>
    </div>

    {/* RIGHT IMAGE */}
    <div className="hidden md:block w-1/2 relative">
      <Image
        src="/teamwork-making-online-blog_53876-94868.jpg"
        alt="Auth"
        fill
        className="object-cover"
        priority
      />
      {/* Overlay */}
      <div className="absolute inset-0 bg-black/40" />

      <div className="absolute bottom-12 left-12 text-white max-w-md">
        <h2 className="text-4xl font-bold mb-3">
          Bring your ideas to life.
        </h2>
        <p className="text-sm text-gray-200">
          Sign up for free and enjoy access to all features for 30 days.
          No credit card required.
        </p>
      </div>
    </div>
  </div>
);
}
