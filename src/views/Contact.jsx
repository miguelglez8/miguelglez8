import { Mail, Github, Linkedin, MapPin } from "lucide-react";

const Contact = () => (
    <section className="contact text-center p-6 bg-white shadow-md rounded-lg">
        <h2 className="text-2xl font-semibold mb-4">Contacto</h2>

        <p className="flex items-center justify-center gap-2 text-lg">
            <Mail className="text-blue-500" size={20} />
            <a
                href="mailto:miguemoreda@gmail.com"
                className="text-blue-600 hover:underline"
            >
                miguemoreda@gmail.com
            </a>
        </p>

        <p className="flex items-center justify-center gap-4 mt-3 text-lg">
            <a
                href="https://www.linkedin.com/in/miguelgonzaleznavarro"
                className="flex items-center gap-2 text-blue-700 hover:text-blue-500"
            >
                <Linkedin size={22}/>
                miguelgonzaleznavarro
            </a>
        </p>

        <p className="flex items-center justify-center gap-4 mt-3 text-lg">
            <a
                href="https://github.com/miguelglez8"
                className="flex items-center gap-2 text-gray-800 hover:text-gray-600"
            >
                <Github size={22}/>
                miguelglez8
            </a>
        </p>

        <p className="flex items-center justify-center gap-2 text-lg mt-3">
            <MapPin className="text-red-500" size={20}/>
            Moreda de Aller, Asturias
        </p>
    </section>
);

export default Contact;
