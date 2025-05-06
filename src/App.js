import React, { useEffect, useState } from 'react';
import './App.css';

const API_URL = 'http://localhost:8080/api/medicamentos';

export default function App() {
  const [tipos, setTipos] = useState([]);
  const [medicamentos, setMedicamentos] = useState([]);
  const [nuevoTipoNombre, setNuevoTipoNombre] = useState('');
  const [nuevoMed, setNuevoMed] = useState({
    codigo: '',
    nombreComercial: '',
    droga: '',
    tipo: { id: '' }
  });
  const [filtroTipo, setFiltroTipo] = useState('');
  const [filtroLetra, setFiltroLetra] = useState('');

  useEffect(() => {
    fetch(`${API_URL}/tipos`)
      .then(res => res.json())
      .then(setTipos);
  }, []);

  const crearTipo = async (e) => {
    e.preventDefault();
    const res = await fetch(`${API_URL}/tipo?nombre=${nuevoTipoNombre}`, {
      method: 'POST'
    });
    if (res.ok) {
      const nuevo = await res.json();
      setTipos([...tipos, nuevo]);
      setNuevoTipoNombre('');
    }
  };

  const eliminarTipo = async (id) => {
    const res = await fetch(`${API_URL}/tipo/${id}`, {
      method: 'DELETE'
    });
    if (res.ok) {
      setTipos(tipos.map(t => t.id === id ? { ...t, eliminado: true } : t));
    }
  };

  const crearMedicamento = async (e) => {
    e.preventDefault();
    const res = await fetch(`${API_URL}/`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(nuevoMed),
    });

    if (res.ok) {
      const nuevo = await res.json();
      setMedicamentos([...medicamentos, nuevo]);
      setNuevoMed({ codigo: '', nombreComercial: '', droga: '', tipo: { id: '' } });
    } else {
      console.error('Error al crear medicamento');
    }
  };

  const aplicarFiltros = async () => {
    let url = '';
    if (filtroTipo) {
      url = `${API_URL}/tipo/${filtroTipo.toLowerCase()}`;
    } else if (filtroLetra) {
      url = `${API_URL}/nombre/${filtroLetra.toLowerCase()}`;
    } else {
      setMedicamentos([]);
      return;
    }

    const res = await fetch(url);
    if (res.ok) {
      const data = await res.json();
      setMedicamentos(data);
    }
  };

  useEffect(() => {
    aplicarFiltros();
  }, [filtroTipo, filtroLetra]);

  const obtenerNombreTipo = (tipo_id) => {
    const tipo = tipos.find(t => t.id === tipo_id);
    return tipo ? tipo.nombre : tipo_id;
  };

  return (
    <div className="app-container">
      <h1>Gestión de Medicamentos</h1>

      <form onSubmit={crearTipo} className="formulario">
        <h2>Crear Tipo de Medicamento</h2>
        <input
          type="text"
          placeholder="Nombre del tipo"
          value={nuevoTipoNombre}
          onChange={e => setNuevoTipoNombre(e.target.value)}
          required
        />
        <button type="submit">Crear</button>
      </form>

      <div className="listado">
        <h2>Tipos de Medicamento</h2>
        <ul>
          {tipos.map(t => (
            <li key={t.id}>
              {t.nombre} {t.eliminado ? '(Eliminado)' : ''}
              {!t.eliminado && (
                <button onClick={() => eliminarTipo(t.id)} style={{ marginLeft: 10 }}>
                  Eliminar
                </button>
              )}
            </li>
          ))}
        </ul>
      </div>

      <form onSubmit={crearMedicamento} className="formulario">
        <h2>Crear Medicamento</h2>
        <input
          type="number"
          placeholder="Código"
          value={nuevoMed.codigo}
          onChange={e => setNuevoMed({ ...nuevoMed, codigo: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Nombre Comercial"
          value={nuevoMed.nombreComercial}
          onChange={e => setNuevoMed({ ...nuevoMed, nombreComercial: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Droga"
          value={nuevoMed.droga}
          onChange={e => setNuevoMed({ ...nuevoMed, droga: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="ID Tipo"
          value={nuevoMed.tipo.id}
          onChange={e =>
            setNuevoMed({
              ...nuevoMed,
              tipo: { ...nuevoMed.tipo, id: Number(e.target.value) }
            })
          }
          required
        />
        <button type="submit">Crear</button>
      </form>

      <div className="filtros">
        <h2>Filtrar Medicamentos</h2>
        <input
          type="text"
          placeholder="Filtrar por letra inicial"
          value={filtroLetra}
          maxLength={1}
          onChange={e => {
            setFiltroLetra(e.target.value);
            setFiltroTipo('');
          }}
        />
        <input
          type="text"
          placeholder="Filtrar por tipo (ej. aerosol)"
          value={filtroTipo}
          onChange={e => {
            setFiltroTipo(e.target.value);
            setFiltroLetra('');
          }}
        />
        <button onClick={aplicarFiltros}>Aplicar Filtros</button>
      </div>

      <div className="listado">
        <h2>Medicamentos</h2>
        <ul>
          {medicamentos.map(m => (
            <li key={m.codigo}>
              <strong>{m.nombreComercial}</strong> - Droga: {m.droga} (Tipo: {obtenerNombreTipo(m.tipo_id)})
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
