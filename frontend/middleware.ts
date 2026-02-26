import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export function middleware(request: NextRequest) {
  const token = request.cookies.get("token")?.value;
  const { pathname } = request.nextUrl;

  // 🔓 Public pages (NO authentication required)
  const publicPages = ["/", "/login", "/signup"];

  if (publicPages.includes(pathname)) {
    return NextResponse.next();
  }

  // 🔒 Protect all other pages
  if (!token) {
    return NextResponse.redirect(new URL("/login", request.url));
  }

  return NextResponse.next();
}

// Apply middleware to pages only (exclude APIs & static files)
export const config = {
  matcher: [
    "/((?!api|_next/static|_next/image|favicon.ico).*)",
  ],
};