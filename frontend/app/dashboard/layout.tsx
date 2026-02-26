import BottomNavbar from "@/components/ui/comp/bottemnav";
import React from "react";

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="min-h-screen bg-black text-white flex justify-center">
      {/* Center feed */}
      <div className="w-full max-w-[470px] min-h-screen bg-black relative">
        
        {/* Header */}
        <header className="sticky top-0 z-40 bg-black border-b border-neutral-800 px-4 py-3">
          <h1 className="text-lg font-semibold text-center">Dashboard</h1>
        </header>

        {/* Content */}
        <main className="px-4 pt-4 pb-6">
          {children}
        </main>

        {/* Bottom Nav → MOBILE ONLY */}
        <div className="md:hidden">
          <BottomNavbar />
        </div>
      </div>
    </div>
  );
}