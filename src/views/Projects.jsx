import { CheckCircle, Calendar, Wrench } from "lucide-react";

const Projects = () => (
    <section
        style={{
            display: "flex",
            flexDirection: "column",
            gap: "20px",
            padding: "20px",
            alignItems: "center",
            justifyContent: "center"
        }}
    >
        <h2 style={{
            fontSize: "24px",
            fontWeight: "bold",
            marginBottom: "20px",
            textAlign: "center",
            width: "100%",
        }}>
            ¡Consulta mis proyectos!
        </h2>

        <div
            style={{
                display: "flex",
                flexWrap: "wrap",
                gap: "20px",
                justifyContent: "space-between",
                width: "100%",
            }}
        >
            <div
                style={{
                    width: "48%",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    transition: "transform 0.3s ease-in-out",
                }}
            >
                <div
                    style={{
                        backgroundImage: "url('/images/foto.jpg')",
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        height: "200px",
                    }}
                ></div>

                <div
                    style={{
                        padding: "20px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        height: "65vh",
                    }}
                >
                    <h3 style={{fontSize: "24px", fontWeight: "bold"}}>
                        Web Portfolio Personal
                    </h3>

                    <h4 style={{fontSize: "18px", fontWeight: "bold"}}>
                        Trabajo autónomo
                    </h4>

                    <div
                        style={{
                            display: "flex",
                            alignItems: "center",
                            gap: "10px",
                            marginBottom: "15px",
                        }}
                    >
                        <p style={{fontSize: "18px", color: "#777"}}>
                            feb. 2025 - Actualidad
                        </p>

                        <span
                            style={{
                                backgroundColor: "#2563eb",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                    En Progreso
                </span>

                        <Calendar size={18} color="#2563eb"/>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            flexWrap: "wrap",
                            gap: "10px",
                            marginBottom: "20px",
                        }}
                    >
                <span
                    style={{
                        backgroundColor: "#2563eb",
                        color: "#fff",
                        padding: "5px 10px",
                        borderRadius: "20px",
                        fontSize: "14px",
                    }}
                >
                    React
                </span>
                        <span
                            style={{
                                backgroundColor: "#34D399",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                    JavaScript
                </span>
                        <span
                            style={{
                                backgroundColor: "#F97316",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                    GitHub Pages
                </span>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            gap: "15px",
                            justifyContent: "flex-start",
                        }}
                    >
                        <a
                            href="https://github.com/miguelglez8/miguelglez8"
                            style={{
                                backgroundColor: "#333",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            GitHub
                        </a>
                        <a
                            href="https://miguelglez8.github.io/miguelglez8"
                            style={{
                                backgroundColor: "#2563eb",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            Web
                        </a>
                    </div>
                </div>
            </div>

            <div
                style={{
                    width: "48%",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    transition: "transform 0.3s ease-in-out",
                }}
            >
                <div
                    style={{
                        backgroundImage: "url('/images/tfg.png')",
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        height: "200px",
                    }}
                ></div>

                <div
                    style={{
                        padding: "20px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        height: "65vh",
                    }}
                >
                    <h3 style={{fontSize: "24px", fontWeight: "bold"}}>
                        Plataforma Web para la coordinación de trabajos académicos
                    </h3>

                    <h4 style={{fontSize: "18px", fontWeight: "bold"}}>
                        Universidad de Oviedo
                    </h4>

                    <div
                        style={{
                            display: "flex",
                            alignItems: "center",
                            gap: "10px",
                            marginBottom: "15px",
                        }}
                    >
                        <p style={{fontSize: "18px", color: "#777"}}>
                            ene. 2024 - jul. 2024
                        </p>

                        <span
                            style={{
                                backgroundColor: "#16A34A",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                        Terminado
                    </span>

                        <CheckCircle size={18} color="#16A34A"/>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            flexWrap: "wrap",
                            gap: "10px",
                            marginBottom: "20px",
                        }}
                    >
            <span
                style={{
                    backgroundColor: "#2563eb",
                    color: "#fff",
                    padding: "5px 10px",
                    borderRadius: "20px",
                    fontSize: "14px",
                }}
            >
                React
            </span>
                        <span
                            style={{
                                backgroundColor: "#F97316",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                Spring Boot
            </span>
                        <span
                            style={{
                                backgroundColor: "#34D399",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                Docker
            </span>
                        <span
                            style={{
                                backgroundColor: "#22D3EE",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                AWS
            </span>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            gap: "15px",
                            justifyContent: "flex-start",
                        }}
                    >
                        <a
                            href="https://github.com/miguelglez8/project-tfg"
                            style={{
                                backgroundColor: "#333",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            GitHub
                        </a>
                        <a
                            href="https://digibuo.uniovi.es/dspace/handle/10651/76268"
                            style={{
                                backgroundColor: "#5db429",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            Documentación
                        </a>
                    </div>
                </div>
            </div>

            <div
                style={{
                    width: "48%",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    transition: "transform 0.3s ease-in-out",
                }}
            >
                <div
                    style={{
                        backgroundImage: "url('/images/footmatch.jpg')",
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        height: "200px",
                    }}
                ></div>

                <div
                    style={{
                        padding: "20px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        height: "65vh",
                    }}
                >
                    <h3 style={{fontSize: "24px", fontWeight: "bold"}}>
                        FootMatch
                    </h3>

                    <h4 style={{fontSize: "18px", fontWeight: "bold"}}>
                        Universidad de Oviedo
                    </h4>

                    <div style={{display: "flex", alignItems: "center", gap: "10px"}}>
                        <p style={{fontSize: "18px", color: "#777"}}>
                            sept. 2023 - ene. 2024
                        </p>
                        <span
                            style={{
                                backgroundColor: "#16A34A",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                        Terminado
                    </span>

                        <CheckCircle size={18} color="#16A34A"/>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            flexWrap: "wrap",
                            gap: "10px",
                            marginBottom: "20px",
                        }}
                    >
            <span
                style={{
                    backgroundColor: "#9C27B0",
                    color: "#fff",
                    padding: "5px 10px",
                    borderRadius: "20px",
                    fontSize: "14px",
                }}
            >
                Kotlin
            </span>
                        <span
                            style={{
                                backgroundColor: "#FF5722",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                Java
            </span>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            gap: "15px",
                            justifyContent: "flex-start",
                        }}
                    >
                        <a
                            href="https://github.com/miguelglez8/footmatch-sdm"
                            style={{
                                backgroundColor: "#333",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            GitHub
                        </a>
                    </div>
                </div>
            </div>

            <div
                style={{
                    width: "48%",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    transition: "transform 0.3s ease-in-out",
                }}
            >
                <div
                    style={{
                        backgroundImage: "url('/images/nemorastur.png')",
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        height: "200px",
                    }}
                ></div>

                <div
                    style={{
                        padding: "20px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        height: "65vh",
                    }}
                >
                    <h3 style={{fontSize: "24px", fontWeight: "bold"}}>
                        Aplicación web para la empresa Nemorastur
                    </h3>

                    <h4 style={{fontSize: "18px", fontWeight: "bold"}}>
                        Trabajo autónomo
                    </h4>

                    <div style={{display: "flex", alignItems: "center", gap: "10px"}}>
                        <p style={{fontSize: "18px", color: "#777"}}>
                            jun. 2023 - ago. 2023
                        </p>
                        <span
                            style={{
                                backgroundColor: "#F59E0B",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                        En Mantenimiento
                    </span>

                        <Wrench size={18} color="#F59E0B"/>
                    </div>


                    <div
                        style={{
                            display: "flex",
                            flexWrap: "wrap",
                            gap: "10px",
                            marginBottom: "20px",
                        }}
                    >
            <span
                style={{
                    backgroundColor: "#9C27B0",
                    color: "#fff",
                    padding: "5px 10px",
                    borderRadius: "20px",
                    fontSize: "14px",
                }}
            >
                Node.js
            </span>
                        <span
                            style={{
                                backgroundColor: "#FF5722",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                MongoDB
            </span>
                        <span
                            style={{
                                backgroundColor: "#00BCD4",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                Docker
            </span>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            gap: "15px",
                            justifyContent: "flex-start",
                        }}
                    >
                        <a
                            href="https://github.com/miguelglez8/app-nemorastur"
                            style={{
                                backgroundColor: "#333",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            GitHub
                        </a>
                    </div>
                </div>
            </div>

            <div
                style={{
                    width: "48%",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    transition: "transform 0.3s ease-in-out",
                }}
            >
                <div
                    style={{
                        backgroundImage: "url('/images/lomap.jpg')",
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        height: "200px",
                    }}
                ></div>

                <div
                    style={{
                        padding: "20px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        height: "65vh",
                    }}
                >
                    <h3 style={{fontSize: "24px", fontWeight: "bold"}}>
                        LoMap
                    </h3>

                    <h4 style={{fontSize: "18px", fontWeight: "bold"}}>
                        Universidad de Oviedo
                    </h4>

                    <div style={{display: "flex", alignItems: "center", gap: "10px"}}>
                        <p style={{fontSize: "18px", color: "#777"}}>
                            feb. 2023 - may. 2023
                        </p>
                        <span
                            style={{
                                backgroundColor: "#16A34A",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                        Terminado
                    </span>

                        <CheckCircle size={18} color="#16A34A"/>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            flexWrap: "wrap",
                            gap: "10px",
                            marginBottom: "20px",
                        }}
                    >
            <span
                style={{
                    backgroundColor: "#9C27B0",
                    color: "#fff",
                    padding: "5px 10px",
                    borderRadius: "20px",
                    fontSize: "14px",
                }}
            >
                React.js
            </span>
                        <span
                            style={{
                                backgroundColor: "#FF5722",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                Node.js
            </span>
                        <span
                            style={{
                                backgroundColor: "#4CAF50",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                SOLID
            </span>
                        <span
                            style={{
                                backgroundColor: "#00BCD4",
                                color: "#fff",
                                padding: "5px 10px",
                                borderRadius: "20px",
                                fontSize: "14px",
                            }}
                        >
                CI/CD
            </span>
                    </div>

                    <div
                        style={{
                            display: "flex",
                            gap: "15px",
                            justifyContent: "flex-start",
                        }}
                    >
                        <a
                            href="https://github.com/miguelglez8/lomap_es5a"
                            style={{
                                backgroundColor: "#333",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            GitHub
                        </a>
                        <a
                            href="https://franciscocoya.github.io/lomapes05a_production/"
                            style={{
                                backgroundColor: "#2563eb",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            Web
                        </a>
                        <a
                            href="https://arquisoft.github.io/lomap_es5a/"
                            style={{
                                backgroundColor: "#5db429",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            Documentación
                        </a>
                        <a
                            href="https://www.youtube.com/watch?v=nkLxMZ8_4Dc"
                            style={{
                                backgroundColor: "#F97316",
                                color: "#fff",
                                padding: "10px 20px",
                                borderRadius: "5px",
                                textDecoration: "none",
                                fontWeight: "bold",
                                transition: "background-color 0.3s ease",
                            }}
                        >
                            Demo
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </section>
);

export default Projects;