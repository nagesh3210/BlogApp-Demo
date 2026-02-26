"use client";

import { Home, Search, PlusSquare, Heart, User } from "lucide-react";
import { usePathname, useRouter } from "next/navigation";

export default function BottomNavbar() {
  const pathname = usePathname();
  const router = useRouter();

  const navItems = [
    { icon: Home, path: "/dashboard" },
    { icon: Search, path: "/search" },
    { icon: PlusSquare, path: "/create" },
    { icon: Heart, path: "/activity" },
    { icon: User, path: "/profile" },
  ];

  return (
    <nav className="fixed bottom-0 left-0 right-0 z-50 border-t bg-white">
      <div className="flex h-14 items-center justify-around">
        {navItems.map(({ icon: Icon, path }) => (
          <button
            key={path}
            onClick={() => router.push(path)}
            className={
              pathname === path ? "text-black" : "text-gray-400"
            }
          >
            <Icon size={26} />
          </button>
        ))}
      </div>
    </nav>
  );
}