import { useEffect, useRef } from "react";

function Background({ children }) {
  const canvasRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d", { alpha: false });
    const DPR = Math.min(window.devicePixelRatio || 1, 2);

    const state = {
      particles: [],
      mouse: { x: 0, y: 0, active: false },
      rafId: null,
      width: 0,
      height: 0,
    };

  
    const NEON = "#00eaff";
    const rand = (min, max) => Math.random() * (max - min) + min;

    function makeParticles() {
      const count = Math.floor((state.width * state.height) / 12000);
      state.particles = new Array(count).fill(0).map(() => ({
        x: rand(0, state.width),
        y: rand(0, state.height),
        vx: rand(-0.04, 0.04),
        vy: rand(-0.04, 0.04),
        r: rand(1, 2.3),
      }));
    }

    function resize() {
      state.width = canvas.clientWidth;
      state.height = canvas.clientHeight;
      canvas.width = Math.floor(state.width * DPR);
      canvas.height = Math.floor(state.height * DPR);
      ctx.setTransform(DPR, 0, 0, DPR, 0, 0);
      makeParticles();
    }

    function step() {
      ctx.fillStyle = "#05070a"; 
      ctx.fillRect(0, 0, state.width, state.height);

      const linkDist = 80;
      ctx.lineWidth = 1;

      // traits entre particules proches
      for (let i = 0; i < state.particles.length; i++) {
        const p = state.particles[i];
        for (let j = i + 1; j < state.particles.length; j++) {
          const q = state.particles[j];
          const dx = p.x - q.x;
          const dy = p.y - q.y;
          const d2 = dx * dx + dy * dy;
          if (d2 < linkDist * linkDist) {
            const a = 1 - Math.sqrt(d2) / linkDist;
            ctx.strokeStyle = `rgba(0, 234, 255, ${a * 0.5})`;
            ctx.beginPath();
            ctx.moveTo(p.x, p.y);
            ctx.lineTo(q.x, q.y);
            ctx.stroke();
          }
        }
      }

      // particules
      for (const p of state.particles) {
        if (state.mouse.active) {
          const dx = p.x - state.mouse.x;
          const dy = p.y - state.mouse.y;
          const repulseRadius = 140;
          const d2 = dx * dx + dy * dy;
          if (d2 < repulseRadius * repulseRadius) {
            const d = Math.sqrt(d2) || 1;
            const force = ((repulseRadius - d) / repulseRadius) * 0.6;
            p.vx += (dx / d) * force;
            p.vy += (dy / d) * force;
          }
        }

        p.x += p.vx;
        p.y += p.vy;

        if (p.x < 0) p.x = state.width;
        if (p.x > state.width) p.x = 0;
        if (p.y < 0) p.y = state.height;
        if (p.y > state.height) p.y = 0;

        ctx.beginPath();
        ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
        ctx.fillStyle = NEON;
        ctx.shadowBlur = 12;
        ctx.shadowColor = NEON;
        ctx.fill();
        ctx.shadowBlur = 0;
      }

      state.rafId = requestAnimationFrame(step);
    }

    function onMouseMove(e) {
      const rect = canvas.getBoundingClientRect();
      state.mouse.x = e.clientX - rect.left;
      state.mouse.y = e.clientY - rect.top;
      state.mouse.active = true;
    }
    function onMouseLeave() {
      state.mouse.active = false;
    }
    function onClick() {
      for (const p of state.particles) {
        const dx = p.x - state.mouse.x;
        const dy = p.y - state.mouse.y;
        const d = Math.hypot(dx, dy) || 1;
        if (d < 160) {
          p.vx += (dx / d) * 1.5;
          p.vy += (dy / d) * 1.5;
        }
      }
    }

    resize();
    window.addEventListener("resize", resize);
    canvas.addEventListener("mousemove", onMouseMove);
    canvas.addEventListener("mouseleave", onMouseLeave);
    canvas.addEventListener("click", onClick);
    state.rafId = requestAnimationFrame(step);

    return () => {
      cancelAnimationFrame(state.rafId);
      window.removeEventListener("resize", resize);
      canvas.removeEventListener("mousemove", onMouseMove);
      canvas.removeEventListener("mouseleave", onMouseLeave);
      canvas.removeEventListener("click", onClick);
    };
  }, []);

  return (
    <div className="background-wrapper">
      <canvas ref={canvasRef} className="bg-canvas" />
      <div className="background-content">{children}</div>
    </div>
  );
}

export default Background;
